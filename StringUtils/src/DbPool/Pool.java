package DbPool;

import javax.sql.PooledConnection;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Vector;

public class Pool {
	private String jdbcDriver = "";
	private String dbUrl = "";
	private String userName = "";
	private String password = "";
	private int initialConnectionsNum = 10;
	private int maxConnectsNum = 20;
	private Vector<MyPooledConnection> connections = null;

	public Pool() {
	}

	public Pool(String jdbcDriver, String dbUrl, String userName, String password) {
		this.jdbcDriver = jdbcDriver;
		this.dbUrl = dbUrl;
		this.userName = userName;
		this.password = password;
		try {

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public synchronized void createPool() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
		if (connections != null)
			return;
		Driver driver = (Driver) (Class.forName(this.jdbcDriver).getDeclaredConstructor().newInstance());
		DriverManager.registerDriver(driver);
		this.connections = new Vector<>();
		createConnects(this.initialConnectionsNum);
	}

	private void createConnects(int num) throws SQLException {
		for (int i = 0; i < num; i++) {
			if (connections.size() >= this.maxConnectsNum) {
				return;
			}
			this.connections.add(new MyPooledConnection(createNewConnect()));
		}
	}

	private Connection createNewConnect() throws SQLException {
		Connection con = DriverManager.getConnection(this.dbUrl, this.userName, this.password);
		if (this.connections.size() == 0) {
			DatabaseMetaData metaData = con.getMetaData();
			int dbMaxConnect = metaData.getMaxConnections();
			if (dbMaxConnect > 0 && this.maxConnectsNum > dbMaxConnect) {
				this.maxConnectsNum = dbMaxConnect;
			}
		}
		return con;
	}

	public synchronized Connection getConnection() {
		Connection con = null;
		if (this.connections == null)
			return con;
		return getFreeConnect();
	}

	private Connection getFreeConnect() {
		Connection con = findConnection();
		if (con == null) {
			try {
				this.createConnects(this.initialConnectionsNum);
				con = findConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return con;
	}

	private Connection findConnection() {
		Connection con = null;
		for (int i = 0; i < this.connections.size(); i++) {
			MyPooledConnection pool = this.connections.get(i);
			if (!pool.isBusy()) {
				con = pool.getCon();
				pool.setBusy(true);
			}
			break;
		}
		return con;
	}

	public void returnConnection(Connection connection) {
		if (this.connections == null)
			return;
		for (int i = 0; i < connections.size(); i++) {
			MyPooledConnection pool = this.connections.get(i);
			if (connection == pool.getCon())
				pool.setBusy(false);
		}
	}

	public synchronized void refreshConnectionPool() {
		if (this.connections == null)
			return;
		for (int i = 0; i < this.connections.size(); i++) {
			MyPooledConnection pool = this.connections.get(i);
			if (pool.isBusy())
				this.wait(5000);

		}
	}

	public void closeConnectionPool() {
		if (this.connections == null)
			return;
		for (int i = 0; i < this.connections.size(); i++) {
			MyPooledConnection pool = this.connections.get(i);
			if (pool.isBusy())
				this.wait(5000);
			this.closeConnection(pool.getCon());
			this.connections.remove(i);
		}
		this.connections = null;
	}

	private void wait(int msecond) {
		try {
			Thread.sleep(msecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
