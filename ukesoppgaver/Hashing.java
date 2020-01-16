//Hashfunksjon:
//Finner en plass i table for hver key.
//Downside: dårlig fordeling hvis tabellstorrelsen blir for stor.
int hash1(String key, int tablesize){
  int hashVal = 0;
  for(int i = 0; i < key.length; i++){
    hashVal += key.charAt(i);
  }
  return (hashVal % tablesize);
}

//Upside: grei fordelig på tilfeldige strenger.
int hash2(String key, int tablesize){
  int hashVal = key.charAt(0) + 27 * key.charAt(1) + 729 * key.charAt(2);
  return (hashVal % tablesize);
}

//Enkel og relativ rask å beregne. God fordeling.
int hash3(String key, int tablesize){
  int hashVal = 0;
  for(int i = 0; i < key.length; i++){
    hashVal = 37 * hashVal + key.charAt(i);
  }
  return (hashVal % tablesize);
}
