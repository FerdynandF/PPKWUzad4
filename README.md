## PPKWU - Programowanie pod kątem wielokrotnego użycia
## Zadanie 4 - Mobilny vCard PŁ
* Proszę przygotować dokumentację i zaimplementować (wg tej dokumentacji) API, które będzie generowało stronę mobilną wykorzystującą wyszukiwarkę pracowników ze strony https://www.p.lodz.pl/pl. Po wyszukaniu wyników (może ich być więcej, niż jeden!!!). Powinna być możliwość kliknięcia na opcję "wygeneruj vCard" dla każdego znalezionego wpisu. Następnie proszę "podpiąć" taką stronę pod swój telefon i sprawdzić, czy działa i czy wygenerowane wizytówki można dodać do kontaktów. Na maksymalną ocenę konieczne jest zaimplementowanie logowania (bez tego maile i numery telefonów są niewidoczne) i "wyciągania" maili i numerów telefonów (np. dla "Nowak") dla wyników wyszukiwania z p.lodz.pl.

 **Dokumentacja API**
 ===================
 
 ## **WEEIA EVENTS**

API generuje listę użytkowników korzystając z wyszukiwarki pracowników na stronie http://www.p.lodz.pl/pl.
API umożliwia rownież generowanie wizytówek vCard, które można dodać do kontaktów.
   
<br /> 

### ENDPOINT
* [GET    /api/weeia/vcard/users](#search-employees)

<br />

<br />  

### **SEARCH EMPLOYEES**
Wyświetla pracowników, których imię i/lub nazwisko pasują do danego paranetru zapytania.



| METODA |     ŚCIEŻKA DOSTĘPU     | PARAMETR |                                                           OPIS                                                           |
| ------ | :---------------------: | :------: | :----------------------------------------------------------------------------------------------------------------------: |
| GET    | `api/weeia/vcard/users` | `search` | Wyświetla znalezionych pracowników. Po wciśnięciu przycisku "Generate vCard" zostaje pobrany vCard dla danego pracownika |
-----
###### Domyślna wartość parametru search: ""

W przypadku nie znalezienia pracownika zostaje wyświetlona o tym informacja.
#### **Przykłady wywołania**

 **Wiele pracowników**
 <br>
Request: 
```java
      GET | localhost:8081/api/weeia/vcard/users?search=Kapusta 
```
Web Response:

<p align="center">
  <img src="https://i.imgur.com/dLoXwh6.jpg" width="350" title="hover text">
</p>
<br>

 **Nie znaleziono pracowników**
 <br>

Request: 
```java
      GET | localhost:8081/api/weeia/vcard/users?search=Kowalkiewicz
```
Web Response:

<p align="center">
  <img src="https://i.imgur.com/iksnGrd.jpg" width="350" title="hover text">
</p>
  
  <br />
  
### **Dodanie pracownika do kontaktów**
##### Po kliknięciu w przycisk "Generate vCard" mamy moliwośc zapisania wizytówki do kontaktów.
 
<p align="center">
  <img src="https://i.imgur.com/5EeIQUo.gif" width="350" title="hover text">
</p>
  


<details>
    <summary>Zawartoś pliku vCard</summary>
    <p>
     
    ``` 
    BEGIN:VCARD
    VERSION:3.0
    N:Kapusta;Imię
    FN:Imię Kapusta
    ORG:I24 - Instytut Informatyki Stosowanej
    END:VCARD
    ```  
</p></details>
