import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int streak=0;
        boolean ganhou = true;

        while (ganhou){
            System.out.println("Sua sequência de vitórias é "+streak+"\n");
            streak++;
            ganhou = jogo();
        }

        }
        public static boolean jogo (){
            Random rnd = new Random();
            int targetDex = rnd.nextInt(1026);
            int tentativas = 0;
            String targetName = dexToName(targetDex);
            System.out.println("***** QUAL O NÚMERO DO POKÉMON *****");
            System.out.println("Objetivo: "+ targetName);
            System.out.println("Acerte o seu número na national dex em 4 tentativas!");
            int t1 = scNum();
            tentativas++;
            if(!ganhou(targetDex, t1)){
                System.out.println(quaoQuente(targetDex, t1));
                System.out.println("Você chutou: "+dexToName(t1));
                int t2 = scNum();
                tentativas++;
                if (!ganhou(targetDex, t2)) {
                    System.out.println(quaoQuente(targetDex, t2));
                    System.out.println("Você chutou: "+dexToName(t2));
                    int t3 = scNum();
                    tentativas++;
                    if (!ganhou(targetDex, t3)) {
                        System.out.println(quaoQuente(targetDex, t3));
                        System.out.println("Você chutou: " + dexToName(t3));
                        int t4 = scNum();
                        tentativas++;
                        if (!ganhou(targetDex, t4)) {
                            System.out.println(quaoQuente(targetDex, t4));
                            System.out.println("Você chutou: " + dexToName(t4));
                            System.out.println("O NÚMERO CORRETO ERA: "+targetDex);
                            System.out.println("Boa sorte na próxima!");
                            return false;
                        }
                    }
                }
            }
            System.out.println("***** PARABÉNS *****");
            System.out.println("O número era "+targetDex);
            System.out.println("Você acertou em "+tentativas+" tentativas!");
            return true;
        }
        public static String dexToName (int dexN){
        try{
            String urlPokeAPI = ("https://pokeapi.co/api/v2/pokemon/"+ dexN);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlPokeAPI)).GET().build();
            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            JsonObject base = JsonParser.parseString(response.body()).getAsJsonObject();
            String nome = base.get("name").getAsString();
            return nome.toUpperCase();
        } catch (Exception e){
            System.out.println("ERRO");
            return null;
        }
    }
    public static int scNum(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while(n<1 || n>1025){
            System.out.println("INVÁLIDO GRR");
            n = in.nextInt();
        }
        return n;
    }
    public static boolean ganhou(int target, int n){
        if (target==n){
            return true;
        } else{
            return false;
        }
    }
    public static Proximidade quaoQuente(int target, int n){
        int a = n-target;
        int aa = Math.abs(target-n);
        if (aa<5){
            if (a<0)System.out.println(">");
            else System.out.println("<");
            return Proximidade.PELANDO;
        }
        if(aa<10){
            if (a<0)System.out.println(">");
            else System.out.println("<");
            return Proximidade.FERVENDO;
        }
        if(aa<30){
            if (a<0)System.out.println(">");
            else System.out.println("<");
            return Proximidade.QUENTE;
        }
        if (aa<70){
            if (a<0)System.out.println(">");
            else System.out.println("<");
            return Proximidade.FRIO;
        }
        if (aa>70){
            if (a<0)System.out.println(">");
            else System.out.println("<");
            return Proximidade.CONGELANDO;
        }
        return null;
    }
    public static Proximidade quaoQuenteFacil (int target, int n){
        int a = n-target;
        int aa = Math.abs(target-n);
        if (aa<5){
            if (a<0)System.out.println(">");
            else System.out.println("<");
            return Proximidade.PELANDO;
        }
        if(aa<10){
            if (a<0)System.out.println(">");
            else System.out.println("<");
            return Proximidade.FERVENDO;
        }
        if(aa<30){
            if (a<0)System.out.println(">");
            else System.out.println("<");
            return Proximidade.QUENTE;
        }
        if (aa<70){
            if (a<0)System.out.println(">");
            else System.out.println("<");
            return Proximidade.AQUECENDO;
        }
        if (aa<150){
            if (a<0)System.out.println(">");
            else System.out.println("<");
            return Proximidade.FRIO;
        }
        if (aa>150){
            if (a<0)System.out.println(">");
            else System.out.println("<");
            return Proximidade.CONGELANDO;
        }
        return null;
    }
    public enum Proximidade { CONGELANDO, FRIO, AQUECENDO, QUENTE, FERVENDO, PELANDO }

}

