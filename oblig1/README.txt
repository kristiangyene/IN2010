Programmet skal kompileres slik:
javac BSTree.java

Mainmetoden ligger i testen min, MyTest.java. mainmetoden i testen vi fikk,
BSTtest.java, kan ogsaa benyttes.
De fleste metodene fra interfacet som skulle implementeres har private hjelpemetoder
som gjoer arbeidet, mens metodene bare kaller paa hjelpemetodene. Vet ikke om
det er en effektiv maate aa gjoere det paa, men det trengtes for aa kunne sende
inn aktuelle parametere uten aa gjoere om paa de metodene vi hadde faatt utdelt.
Jeg valgte aa ikke gjøre ekstraoppgavene p.g.a det ville tatt veldig lang tid,
men kommer til aa gjoere de senere uansett.
Antagelser:
-Foreldrepeker kan brukes.
-Naar en lik value blir lagt til skal den settes paa venstre side.
-FindInRange(low, high) skal returnere verdier som er lik og mellom low og high.

Saeregenskaper:
-Metode, smallestValue(), som finner minste verdien. Brukes i remove().

Har ingen spesielle Saeregenskaper enn at jeg bruker hjelpemetoder for å få til
det meste. Kanskje flere enn nødvendig. Proever å gjoere de fleste metodene
rekursivt, og trengte derfor metoder med flere parametere.

Det kan vaere tilfeller der remove() ikke fungerer som den skal. Hadde vaert
fint om det var en enklere maate aa gjoere det paa, eller noen tips til aa
luke ut alle tilfeller.
