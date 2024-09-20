public class SenhaCriptografada {
    public static String criptografada(String senha) {
        StringBuilder cripto = new StringBuilder();
        for (char c : senha.toCharArray()) {
          cripto.append((int) c).append(" ");
    }
    return cripto.toString().trim();
 }
    public static String descriptografad(String senha) {
        StringBuilder descriptografada = new StringBuilder();
        String[] senhaDividida = senha.split(senha);
        for (String descripto : senhaDividida) {
            int v = Integer.parseInt(descripto);
            descriptografada.append((char) v);
        }

        return senha.toString();
    }
}

