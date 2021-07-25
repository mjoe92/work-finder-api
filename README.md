# job-finder-api



A feladat:
A cél egy több forrásból adatokat gyűjtő álláskereső API létrehozása.
A feladatot Java 11 nyelven, Spring framework segítségével valósítsd meg. Adatok tárolására
bármilyen SQL vagy NoSQL adatbázist használhatsz.
Követelmények:
1. Az alkalmazás biztosítson lehetőséget kliensalkalmazások regisztrációjára (POST /clients). A
   kliens átadja a nevét (validáció: max 100 karakter), e-mail címét (validáció: érvényes email
   cím formátum, bármilyen regexp használatával, valamint egyediség ellenőrzése). A
   responseban a szerver egy api kulcsot ad vissza UUID formátumban.
2. Az alkalmazás biztosítson lehetőséget állások létrehozására (POST /positions). A kliens
   átadja az állás megnevezését(validáció: max 50 karakter), a munkavégzés földrajzi
   helyét(validáció: max 50 karakter). A szerver első lépésben ellenőrzi az api kulcs
   érvényességét. Nem érvényes api kulcs esetén hibaüzenettel tér vissza. A szerver mentse el
   az állást, majd térjen vissza egy URL-lel a responseban, hogy milyen oldalon érhető el a
   pozició.
3. Az alkalmazás biztosítson lehetőséget állások keresésére (GET /positions). A kliens
   átadja a keresett keywordöt (pl.: "finance", validáció: max 50 karakter) valamint a
   lokációt (pl.: "london", validáció: max 50 karakter). A szerver első lépésben ellenőrzi az
   api kulcs érvényességét. Nem érvényes api kulcs esetén hibaüzenettel tér vissza.
   Érvényes api kulcs esetén az átadott adatokkal bekérdez a következő helyekre:
   ○ GitHub Job API (https://jobs.github.com/api)
   ○ Adatbázisban tárolt állások

4. A fenti adatokat össze kell fésülni és a kliens alkalmazásnak visszaadni a következő
   adatokat:
   ○ Job title - az állás megnevezése
   ○ Location - a munkavégzés földrajzi helye
   ○ URL - a hírdetéshez tartozó URL

A feladat megoldása közben ha bármilyen problémába ütközöl az API használata során
(nem elérhető, bizonytalanul elérhető), akkor bármilyen más, ingyenes API-t használhatsz a
megoldáshoz (Itt találsz egy listát az elérhető API-król:
https://github.com/toddmotto/public-apis#jobs).
A megoldás során próbáld generikusan kialakítani a rendszert, hogy igény esetén könnyen
tovább bővíthető legyen plusz adatforrások bevonásával.
A szerver validációs hibák esetén egységes hibatípussal térjen vissza, részletezve, hogy
milyen mezőkkel milyen validációs hiba történt.
A megoldást kérlek, töltsd fel GitHub vagy GitLab-ra és commitold lépésről-lépésre, hogy
lássam, milyen fázisokban haladtál. Készíts hozzá egy rövid leírást is, ami leírja, hogyan
indítható el az alkalmazás és a hozzá tartozó tesztek.

A feladat kb 4 órát vesz igénybe, amennyiben nem sikerül időn belül megoldani a feladatot,
akkor térj ki arra, hogy melyik részek nincsenek készen és mennyi idő lenne implementálni a
többi részt is.
A fejlesztés után sorolj fel egy legalább 5 elemű listát arról, hogy milyen további lépéseket
tennél ezzel a projekttel kapcsolatban, hogy teljes mértékben production ready alkalmazás
legyen és az üzemeltetés is elfogadja tőlünk ezt az alkalmazást.