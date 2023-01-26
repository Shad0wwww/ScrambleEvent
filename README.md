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

      
