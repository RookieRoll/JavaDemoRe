package DbPool;

import java.sql.Connection;

public class MyPooledConnection {
    private Connection con=null;
    private boolean isBusy=false;

    public MyPooledConnection(Connection con){
        this.con=con;
    }


    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
