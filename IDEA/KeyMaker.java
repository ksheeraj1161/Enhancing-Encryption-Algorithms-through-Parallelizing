package IDEAAlgorithm.IDEA_Parallel;

import IDEAAlgorithm.commons.Key;
import IDEAAlgorithm.commons.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException; import java.util.concurrent.ExecutorService; import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class KeyMaker {
Key makeKey(Key key) {
try {
int numberOfCores = 8;
ExecutorService service = Executors.newFixedThreadPool(numberOfCores); String keyString = key.getOriginalKeyString();
HashMap<Integer, Future<String>> taskMap = new HashMap<>(); for
(int i = 0; i < 7; i++) {
taskMap.put(i, service.submit(new KeyMakerTask(keyString, 25 * i))); } String megaKey = "";
for (int i = 0; i < 7; i++) {
megaKey = megaKey.concat(taskMap.get(i).get());
}

ArrayList<String[]> keyList = new ArrayList<>();
 
String[] temp;
int index = 0, limit;
for (int i = 0; i < 9; i++) { limit = i == 8 ? 4 : 6; temp = new String[limit];
for (int j = 0; j < limit; j++) {
temp[j] = megaKey.substring(index, index + 16); index += 16;
}
keyList.add(temp);
}
service.shutdown(); key = new Key(keyList);
} catch (InterruptedException | ExecutionException e) { e.printStackTrace();
}
return key;
}

Key invertKey(ArrayList<String[]> myList) {
try {
for (int i = 0; i < 9; i++) {
System.arraycopy(myList.get(i), 0, KeyInverterTask.store[i], 0, myList.get(i).length); }
ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); HashMap<Integer, Future<String[]>> taskMap = new HashMap<>();
for (int i = 0; i < 9; i++) {
taskMap.put(i, service.submit(new KeyInverterTask(i)));
}

ArrayList<String[]> newList = new ArrayList<>(); for (int i = 0; i < 9; i++) { newList.add(taskMap.get(i).get());
}
service.shutdown();
return new Key(newList);
} catch (InterruptedException | ExecutionException e) { e.printStackTrace();
}
return null;
}
}

class KeyMakerTask implements Callable<String> {

private String data; private int times;

KeyMakerTask(String data, int times) {
this.data = data;
this.times = times;
}

@Override
public String call() { if (times == 0) { return data;
}
if (times > 128) {
times = times % 128;
 
}
String finalNumber = ""; int i;
for (i = 0; i < times; i++) {
finalNumber = finalNumber.concat(Character.toString(data.charAt(i)));
}
finalNumber = data.substring(times).concat(finalNumber); return finalNumber;
}
}
class KeyInverterTask implements Callable<String[]>
{ private int i;
private final Utilities utilities = new Utilities();
static String[][] store = new String[9][6];

KeyInverterTask(int i) {
this.i = i;
}

@Override
public String[] call() {
if (i != 8) {
return new String[]{ utilities.findInverse(KeyInverterTask.store[8 - i][0]), utilities.findNegative(KeyInverterTask.store[8 - i][1]), utilities.findNegative(KeyInverterTask.store[8 - i][2]), utilities.findInverse(KeyInverterTask.store[8 - i][3]), KeyInverterTask.store[8 - i - 1][4], KeyInverterTask.store[8 - i - 1][5]
};
} else {
return new String[]{ utilities.findInverse(KeyInverterTask.store[8 - i][0]), utilities.findNegative(KeyInverterTask.store[8 - i][1]), utilities.findNegative(KeyInverterTask.store[8 - i][2]), utilities.findInverse(KeyInverterTask.store[8 - i][3]), };
}
}
}
