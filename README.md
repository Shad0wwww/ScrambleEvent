# ScrambleEvent
Et plugin jeg har lavet til en superwaesome server, som hedder "spyd"



**Commands du kan bruge:**
```
scramble, scr og scrambleEvent
```

**Hvordan får man wins i skript?:**

```
import:
  dk.shadow.utils.getBalance

command /getwinsdata: 
  trigger:
      set {_v} to Wins.getBalance(player)
      send "%{_v}% " to player
```


**Addons / plugins du skal bruge for at få pluginet til at virke med skript**

```
Essentials.jar
Vault.jar
skript.jar
skript-reflect.jar
```  


**Addons / plugins du skal bruge for at få pluginet til at virke**

```
Essentials.jar
Vault.jar
```      



*Copyright © 2022 by Shad0wsense. All rights reserved. This material may not be published, broadcast, rewritten or redistributed without permission.*
