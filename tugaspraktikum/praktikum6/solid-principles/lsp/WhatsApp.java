package lsp;

public class WhatsApp implements VideoGroupManager {

    @Override
    public void chat() {
        System.out.println("Fitur Chat");
    }

    @Override
    public void sendPhotosAndVideos() {
        System.out.println("Fitur pengriman foto dan video");
    }

    @Override
    public void callGroupVideo() {
        System.out.println("Fitur telepon video grup");
    }
    
}
