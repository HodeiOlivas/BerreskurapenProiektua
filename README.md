# Berreskurapen Proiektua

## Liburuak

Nire proiektuaren gai nagusia liburuak izango dira. Aukeratu dudan Json fitxategia progrmazio-liburu buruzko datuak gordetzen ditu.

Progrmazio-liburuen buruzko **datu** hauek gordetzen ditu:
- Liburuaren titulua **(String)**
- Isbn-a **(int)**
- Orrialde kopurua **(int)**
- Irteera-data **(Objetua)**
- Liburuaren argazkia **(String)**
- Deskribapen laburra **(String)**
- Estatusa: Argitaratuta/Edo ez **(String)**
- Autoreak **(Arraya)**
- Kategoriak **(Arraya)**

### Programazio liburuak

Url: https://github.com/bvaughn/infinite-list-reflow-examples/blob/master/books.json

#### **RestApian** momentuz erakutziko ditudan eragiketak:
- **liburuak/liburuGuztiak:**
  - **Mota:** GET
  - **Azalpena:** Liburu guztien informazio guztia ikusteko erabiliko da.
- **liburuak/liburuByTitle{title}:**
  - **Mota:** GET
  - **Azalpena:** Liburu baten izenburua bilatuta bere datuak ikusteko.
      - Titulua ez bada aurkitzen mezu bat agertuko da, idatzi duzun titulua ez dagoela edo txarto idatzita dagoela esanez.
- **liburuak/liburuBerria:**
  - **Mota:** POST
  - **Azalpena:** Liburu berri bat txertatzeko balio du.
      - Sortutako liburu berriari Isbna automatikoki sortuko da.
      - 10 karaktereko zenbakia izango da. 
- **liburuak/liburuenEstatusa:**
  - **Mota:** PUT
  - **Azalpena:** Liburuaren titulua eskatuko da.Liburua publikoa bada eta hoinarte, "ezetz" jarrita badago aldatuko da. 
- **liburuak/liburuaEzabatu:**
  - **Mota:** DELETE
  - **Azalpena:** Datu-basetik tituluaren bitartez edozein liburu ezabatu ahalko da. 
- **liburuak/liburuLodienak:**
  - **Mota:** GET
  - **Azalpena:** Liburu handienetik txikienerarte lista ikusiko da (Orrialde kopurua kontuan izanda). 
- **liburuak/AutoreenLiburuak:**
  - **Mota:** GET
  - **Azalpena:** Autorea bilatuta bera idatzitako liburuen zerrenda adieraziko da. 
