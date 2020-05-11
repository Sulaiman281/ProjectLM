package sample;

public class Crypt {

    public byte[] encrypt(byte[] _data){
        byte[] enc = new byte[_data.length];
        for(int i = 0; i<_data.length; i++){
            enc[i] = (byte) (i % 2 == 0 ? _data[i]+5 : _data[i]-10);
        }
        return enc;
    }
    public byte[] decrypt(byte[] _data){
        byte[] dec = new byte[_data.length];
        for(int i = 0; i<_data.length; i++){
            dec[i] = (byte) (i % 2 == 0 ? _data[i]-5 : _data[i]+10);
        }
        return dec;
    }
}
