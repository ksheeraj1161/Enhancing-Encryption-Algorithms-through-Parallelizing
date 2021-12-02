package IDEAAlgorithm.commons;

import java.util.ArrayList;

public class Key {
private ArrayList<String[]> keyList;

public Key() {
keyList = new ArrayList<>();
}

public Key(ArrayList<String[]> keyList) {
this.keyList = keyList;
}

public ArrayList<String[]> getKeyList() {
return keyList;
}
public void setKeyList(ArrayList<String[]> keyList) {
this.keyList = keyList;
}

public String getOriginalKeyString() {
return "100101110111110000001110011000101011111000001111001110001001101011100011101110011010100000010000101010000 10100100001101111110101";
}
}
