package banking.logincabinet.dataonscreen;

import banking.logincabinet.Window;

public class WindowFactory {
    public Window getCabinet(boolean logged){

        if(logged == true){
            return new LoggedWindow();
        }
        if(logged == false){
            return new LogingWindow();
        }

        return null;
    }
}
