# job-finder-api
A feladat:
A cél egy több forrásból adatokat gyűjtő álláskereső API létrehozása.
Követelmények:
1. Kliensalkalmazások regisztrációja: a kliens átadja a nevét (validáció: max 100 karakter), 
   e-mail címét (validáció: érvényes email cím formátum, bármilyen regexp használatával,
   valamint egyediség ellenőrzése). A responseban a szerver egy api kulcsot ad vissza UUID formátumban.
2. Állások létrehozása: kliens átadja az állás megnevezését(validáció: max 50 karakter), 
   a munkavégzés földrajzi helyét(validáció: max 50 karakter).
   A szerver első lépésben ellenőrzi az api kulcs érvényességét. Nem érvényes api kulcs esetén hibaüzenettel tér vissza.
   A szerver mentse el az állást, majd térjen vissza egy URL-lel a responseban, hogy milyen oldalon érhető el a pozició.
3. Az alkalmazás biztosítson lehetőséget állások keresésére. A kliens átadja a keresett keywordöt
   (pl.: "finance", validáció: max 50 karakter) valamint a lokációt (pl.: "london", validáció: max 50 karakter).
   A szerver első lépésben ellenőrzi az api kulcs érvényességét.
   Nem érvényes api kulcs esetén hibaüzenettel tér vissza.
4. Érvényes api kulcs esetén az átadott adatokkal bekérdez a következő helyekre (pl.):
      Jooble
      Adzuna

Kiegészítések:
1. Alap CRUD mindkettőre JSON objektum átvitellel + GET / POST a 
   "/clients/{client_id}/works/{work_id}" végponton állás lekérésre / felvételre kliensre
2. Minden field valamilyen mértékű validáláson fog átesni
3. Egységes hibatípussal térjen vissza, részletezve, hogy milyen mezőkkel milyen validációs hiba történt.

Opcionális:
1. UI formot tartalmazzon, amely a munkakereső oldal kiválasztásával változhat
2. Könnyű bővíthetőség érdekében (controller és service rétegben) generikus megoldás
