# Berreskurapen Proiektua
## BIDEOA YT: https://youtu.be/sFAjC4khYbc
## Programazio liburuak

Nire proiektuaren gai nagusia liburuak izango dira. Aukeratu dudan Json fitxategia programazio-liburu buruzko datuak gordetzen ditu.

Programazio-liburuen buruzko **datu** hauek gordetzen ditu:
- Liburuaren titulua **(String)**
- Isbn-a **(int)**
- Orrialde kopurua **(int)**
- Irteera-data **(Objetua)**
- Liburuaren argazkia **(String)**
- Deskribapen laburra **(String)**
- Estatusa: Argitaratuta/Edo ez **(String)**
- Autoreak **(Arraya)**
- Kategoriak **(Arraya)**
- Puntuazioa **(Arraya Objetuena)**

### Json fitxategia

Url: https://github.com/bvaughn/infinite-list-reflow-examples/blob/master/books.json

#### **RestApian** erakutziko ditudan eragiketak:
- **programazioLiburuak/puntuazioa/{title}:**
  - **Mota:** PUT
  - **Azalpena:** Liburuaren titulua eskatuko da.Puntuazio berri bat sartzeko aukera izango duzu. 
- **programazioLiburuak/liburuenEstatusa:**
  - **Mota:** PUT
  - **Azalpena:** Liburuaren titulua eskatuko da.Liburua publikoa bada eta hoinarte, "ezetz" jarrita badago aldatuko da. 
- **programazioLiburuak/liburuBerria:**
  - **Mota:** POST
  - **Azalpena:** Liburu berri bat txertatzeko balio du.
      - 10 karaktereko isbn zenbakia izan behar da. 
      - Estatusa derrigorrean MEAP edo PUBLISH izan behar dira.
      - Deskribapena 60 hitz maximo izan ahal ditu.
- **programazioLiburuak/publicatiedate/{year}:**
  - **Mota:** GET
  - **Azalpena:** Urtea jarrita, urte horretararte sortu ziren liburuen zerrenda ikusiko da. Urte hurbilenetik zaharrenera.
- **programazioLiburuak/liburuak/Kategoria/{categories}:**
  - **Mota:** GET
  - **Azalpena:** Kategoria bat idatzita kategoria hori duten liburu guztiak agertuko dira.
- **programazioLiburuak/liburuByTitle{title}:**
  - **Mota:** GET
  - **Azalpena:** Liburu baten izenburua bilatuta bere datuak ikusteko.
      - Titulua ez bada aurkitzen mezu bat agertuko da, idatzi duzun titulua ez dagoela edo txarto idatzita dagoela esanez.
- **programazioLiburuak/liburuTamaña:**
  - **Mota:** GET
  - **Azalpena:** Liburu handienetik txikienerarte lista ikusiko da (Orrialde kopurua kontuan izanda).Orri kopuruaren minimoarekin bilatzen da. 
- **programazioLiburuak/liburuGuztiak:**
  - **Mota:** GET
  - **Azalpena:** Liburu guztien informazio guztia ikusteko erabiliko da.
- **programazioLiburuak/AutoreenLiburuak:**
  - **Mota:** GET
  - **Azalpena:** Autorea bilatuta bera idatzitako liburuen zerrenda adieraziko da. 
- **programazioLiburuak/liburuaEzabatu:**
  - **Mota:** DELETE
  - **Azalpena:** Datu-basetik tituluaren bitartez edozein liburu ezabatu ahalko da. 


