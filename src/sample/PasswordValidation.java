package sample;

public class PasswordValidation {

    private String pass;

    public PasswordValidation(String _pass){
        setPass(_pass);
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean valid(){
        return uppercase() && lowercase() && number() && valid_size();
    }

    public boolean uppercase(){ return count('A','Z') > 0; }
    public boolean lowercase(){
        return count('a','z') > 0;
    }
    public boolean number(){
        return count('1','9') > 0;
    }
    public boolean valid_size(){
        return pass.length() >= 8 && pass.length() <= 15;
    }

    public int count(char a,char z){
        int c = 0;
        for(int j = 0; j< pass.length(); j++){
            for(char i = a; i<=z; i++){
                if(pass.charAt(j) == i){
                    c++;
                    break;
                }
            }
        }
        return c;
    }

    public String getPass() {
        return pass;
    }
}
