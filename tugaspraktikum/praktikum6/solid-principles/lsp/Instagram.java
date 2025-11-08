package lsp;

public class Instagram implements PostMediaManager {

    @Override
    public void chat() {
        System.out.println("Fitur Chat");
    }

    @Override
    public void sendPhotosAndVideos() {
        System.out.println("Fitur pengriman foto dan video");
    }

    @Override
    public void publishPost() {
        System.out.println("Fitur posting feed");
    }
    
}
