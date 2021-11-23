
# Dokumentacja projektu "MasterMind"

Niniejszy dokument stanowi dokumentację projektu MasterMind tworzonego w ramach przedmiotu Technologie Obiektowe.
  
Autorzy: Paulina Adamczyk, Zuzanna Brzezińska, Jakub Margol, Wojciech Piechaczek 

  
  
  INSTRUKCJA URUCHOMIENIA APLIKACJI:
	  W folderze *executable/bin/* znajduje się  plik wykonywalny *MastermindProject.bat*.  Aby uruchomić program należy pobrać repozytorium a następnie dwukrotnie kliknąć prawym przyciskiem myszy na plik wykonywalny (ewentualnie z poziomu konsoli wywołać *./MastermindProject.bat*)

Spis Treści

 1. [Cel](#1-cel)
 2. [Działanie aplikacji ](#2-działanie-aplikacji)
 3. [Środowisko](#3-środowisko) 
 4. [Diagram UML](#4-diagram-uml)
 5. [Poszczególne elementy aplikacji](#5-poszczególne-elementy-aplikacji) 
		 * AppNavigator  
		 * Menu  
		 - Ekran gry  
		 - Logowanie/Rejestracja
		 - Ekran statystyk
		 - System powiadomień 
		 - Drag and Drop
 6. Model  danych


## 1. Cel
 

Celem projektu jest stworzenie aplikacji umożliwiającej użytownikowi przeprowadzenie rozgrywki w grze Master Mind (polegającej na odgadnięciu w określonej liczbie tur kodu złożonego z danej liczby kolorów). Program powinien zapewniać interfejs graficzny oraz persystencję danych, takich jak użytkownicy i ich rozgrywki. 

  

## 2. Działanie aplikacji
    

Po uruchomieniu aplikacji pojawia się menu, w którym użytkownik może wybrać poziom trudności gry, wejść w statystyki oraz wybrać jedną z dwóch dostępnych opcji: zalogować się lub dokonać rejestracji. W obu przypadkach na ekranie pojawia się okno logowania/rejestracji w którym użytkownik może wpisać potrzebne dane. Po autentykacji, okno to zamyka się i z powrotem jesteśmy w menu. Po naciśnięciu przycisku “start” gra rozpoczyna się.


Zakładamy, że zasady rozgrywki są znane.

 
Kiedy graczowi nie uda się rozwiązać zagadki, może on wrócić do menu głównego i ponownie wybrać poziom. W przeciwnym wypadku wyliczany jest wynik gracza na podstawie liczby podjętych prób odgadnięcia rozwiązania, poziomu trudności(od wyboru poziomu trudności zależy liczba dostępnych prób dopasowania wzorca oraz liczba kolorów do dyspozycji) oraz od czasu w jakim udało się ukończyć grę. W przypadku, kiedy wyliczony wynik łapie się do trzech najlepszych, do wszystkich użytkowników zarejestrowanych w grze wysyłane jest powiadomienie o zmianie leaderów w rankingu.


Z menu głównego istnieje również możliwość przejścia do widoku statystyk, gdzie prezentuje się 20 najlepszych graczy wraz z wynikami, jakie uzyskali.

  
  

![](https://lh6.googleusercontent.com/_CARcgCohdLZC6OvgFtFr1riaJVXGZxgzLLhjnRShlA0cL4hJ0VR2ndVuHJmVGp0alvu3pvBnOzCrNHQmelWxN7AVBu7khnTPtHAoG3qllLnwHEgcatGvW3BUINW_q0aZJA8Ctgs)

  
  
  

## 3. Środowisko
    

  

Gra będzie miała charakter gry wolnostojącej napisanej w języku java, przy pomocy frameworka Spring. Część danych będzie przechowywanych w bazie danych MySQL, z którą komunikować będziemy się za pomocą Hibernate. Za wygląd naszej aplikacji odpowiada biblioteka JavaFX, zapewniająca potrzebny interfejs graficzny.

  

## 4. Diagram UML
    

  

Poniżej przedstawiamy diagram klas całej aplikacji. Zgodnie zdecydowaliśmy, że na diagramie klas nie zamieścimy widoków. Będą one przedstawione na osobnych schematach.

  

![](https://lh4.googleusercontent.com/xaFjfey2FCpjFIP4tRgNLHB_FPrK4vGKxPh6xl3mhF8NXYerPNidKxKHkLqi5Min6Edy3Bc72QXkqN41skiHL86rSdHcAzU3csDHhFQFZdLu3pF8ZAE9JUCeMo-5SIxXFUDGd66r)

  

W następnym rozdziale zostaną omówione poszczególne części zaprezentowanego diagramu.

  

W celu ułatwienia interpretacji diagramu UML, poniżej przedstawiamy podział klas na poszczególne warstwy:

  

 - dostępu danych
 - logiki biznesowej
 - prezentacyjną

    

  

![](https://lh5.googleusercontent.com/mN8mA_4iR6JRzSnfoHmmfUhuZeWCBhgsukhGui0FDvpErRu64X2C0fhfjkvwBukPUBf9IkMQU6hSkq7FL2AZcpCUwXfxU9uGWD-01PNBj9-APDcb5M29EHOjDm9gPfdI9dutkNVB)

  
  
  

## 5. Poszczególne elementy aplikacji
    

  
  

### 5.1. AppNavigator
    

![](https://lh6.googleusercontent.com/QYXnLs92U45X3e2zjcuQfJyIFBzqrSC3xGh-lruTEXatbYAPUmxB9-BPn700bOY4UaAa05Zr440QYhYZho2bcStCfOIu0aiuvVwRYat2ijf5qY5djidImtN6eTfelqmsfATsr1__)

Klasa ta odpowiada za przełączanie poszczególnych okien w aplikacji w zależności od potrzeby użytkownika. Jest on powiązany z klasami:

-   MenuController
-   GamePresenter
-   LoginPresenter
-   RegisterPresenter
-   ScoreBoardController
-   MenuModel
-   GameModel
-   LoginModel
-   RegisterModel
-   ScoreBoardModel
-   StatsPresenter
-   StatsModel
    

Klasy typu Presenter i Controller zawierają również instancje AppNavigatora - umożliwia im to odpowiednią reakcję na dane wejściowe użytkownika.

  
  

### 2. Menu
    

  

Składa się na nie klasa MenuController, MenuModel oraz widok odpowiedzialny za wygląd MenuView.

  

Do interakcji widoku z modelem obiektu użyliśmy wzorca MVC, który prezentuje się następująco:

  
  

![](https://lh3.googleusercontent.com/iOL2m6pgelNyC6-F9ZPh488ZQ-7fR7yRH2QO30NCltxCRJisxvnP44tv-rEM4o_EQ5Dbj4EYmoCbinUoxCyNgMVjMnbN8dTviB71AfEOFX_y9juc448_hHXOjC4VfItJjRzr8Qgs)

  
  

Jak widać na powyższym schemacie klasa MenuController jest obserwatorem MenuView. Ten z kolei jest obserwowany i gdy nastąpi w nim zmiana informuje o tym swojego obserwatora.

Klasa ta posiada atrybuty user i difficulty - te dwa aspekty gry są już ustalane na poziomie menu i przekazywane dalej do prezentera gry poprzez klasę AppNavigator.

  
  
  
  
  
  
  
  
  
  
  

### 3. Ekran gry
    

  

Funkcjonalność ekranu z grą oparta jest na działaniu wzorca MVP, którego schemat zamieszczony jest poniżej:

  

![](https://lh6.googleusercontent.com/yn2rf1bst3Ko3fFfEdtzuA8q5WNLhjUSebgs3YmDJS0eLJE_D6WGDzNHHedwu3XvVEkckgDMrdtUOcJMsJLl28SRRdk3JQZOSllgpfd1pk4YNb6Ux_tpcNsd6oa2Xbvt8-tnrlwl)

  

W tym rozwiązaniu GamePresenter obserwuje widok i na tej podstawie dokonuje zmian w nim oraz w modelu gry.

Klasa GameModel zawiera obiekty klasy Game i GameDao - dzięki temu możliwa jest persystencja statystyk z poszczególnych rozgrywek . Dodatkowo ma atrybuty przechowujące poprawny kod, czas rozpoczęcia rozgrywki, numer rundy, aktualnie podaną przez gracza sekwencję kolorów oraz singleton ScoreComparator (odpowiadający za wybór czy i do których graczy wysłać maile).

  
  
  

### 4. Logowanie/Rejestracja
    

  

Podobnie jak w przypadku ekranu gry, w celu realizacji zarówno logowania, jak i rejestracji użytkownika postawiliśmy na wzorzec MVP (schemat analogiczny jak w podpunkcie d), z tą różnicą, że zamiast dwóch osobnych klas modelowych, oba Prezentery używają klasy UserModel i w niej dokonują zmian.

  

### 5. Ekran rankingu
    

  

Analogicznie jak w podpunkcie dotyczącym obsługi głównego menu gry, zastosowaliśmy tutaj wzorzec MVC (schemat analogiczny jak przy opisie Menu), który na bieżąco uaktualnia tabele statystyk po otrzymaniu nowego wyniku.

  

### 6. Ekran statystyk
    

  

W przypadku ekranu pokazującego statystyki zalogowanego gracza wykorzystywany jest wzorzec mvp - klasa StatsPresenter jest odpowiedzialna za obsługę tego widoku i na podstawie klasy StatsModel prezentuje dane o tym w ilu grac użytwkowik brał udział i jakie jest jego ratio przegranych do wygranych.

  

### 7. System powiadomień
    

  

Aby zrealizować system powiadomień, który wysłałby maile z informacją o zmianie 3 najlepszych wyników w rankingu do wszystkich graczy, użyliśmy klasy ScoreComparator. Jej zadaniem jest informowanie klasy mailService o konieczności wysyłania powiadomień do użytkowników aplikacji, gdy zajdzie taka potrzeba. O tym czy mail ma zostać wysłany, decyduje na podstawie wyniku wyliczonego w klasie Score, na podstawie przebiegu rozgrywki. Założyliśmy, że na końcowy wynik gracza składa się liczba prób, w jakiej udało mu się rozwiązać problem, poziom trudności oraz uzyskany czas.

  

### 8. Drag and Drop
    

  

Główny ekran gry jest kontrolowany przez klasę GamePresenter oraz widok GameView.

GamePresenter na podstawie dostarczonej konfiguracji produkuje odpowiednią dla danego

poziomu trudności planszę gry, wykorzystując do tego klasę SlotRowView, która odpowiada

za stworzenie pojedynczego wiersza miejsc przeznaczonych na kulki, oraz ColorDragView odpowiadającą za stworzenie osobnych kulek.

  

Klasa ColorSlotView odpowiada za wygląd otworu na kulkę i jego zachowanie, w szczególności za przechwycenie upuszczonej do niego kulki (o ile nie jest zablokowany).

  

Klasa ColorDragView odpowiada za wygląd kulki oraz umożliwia jej podniesienie i przesunięcie.

  

Klasa SlotRowView tworzy rząd z podanej ilości miejsc (ColorSlotView) oraz dodaje do niego wskaźnik dla ilości poprawnych lub częściowo poprawnych ustawień.

  
  
  
  
  
  

## 6. Baza danych
    

  

Schemat bazy danych prezentuje się następująco:

  
  

![](https://lh4.googleusercontent.com/ufXctJL_beOr-bXzyYJQbPlMBN4K6DOW1G6e-rhNFuRVeeZEE4sAJ427yereeUJ4eBGAhwqdaU7SA-zEdz9agqjO1gVGXlLKa2dH1dFkKRzhYv-ajfSKtjUTtw-V2SbUr-zzNrIK)

  

Jak można zobaczyć na zamieszczonym powyżej schemacie, tabela User jest w relacji jeden do wielu z tabelą RecordedGames, co oznacza, że jeden użytkownik może posiadać wiele różnych odbytych gier.

  

Aby rozróżnić elementy bazy danych od modelu postanowiliśmy użyć jednego z wzorców persystencji, a dokładniej Object-Relational Mapping.

  

![](https://lh6.googleusercontent.com/yAR4EPY7c1kfU0f-8TpWeLsf8vYofvtd8o1CPqB2q_Jn29YoBiJCd68qBh625x0jDu4woc3O35hmh21cxUTq1tUoR6vIgK6uz0_49Imr73lE2mNYhMAng8N2cgCdoYgB6OP-48V1)

  

W modelu tym na samej górze hierarchii znajduje się klasa GenericDao zawierająca metody update, save oraz CurrentSession, gdzie dwie pierwsze odpowiadają za modyfikację bazy. Rozszerzają ją dwie klasy: GameDao oraz UserDao, implementujące kolejno interfejsy: IGameDao oraz IUserDao. Klasy te mają na celu realizować dodatkowe operacje na bazie danych.
