bp3flash.py – Anpassung für den aktuellen mmc-utils-Quellstand
===============================================================

Ziel
----
Diese Fassung passt die RPMB-Aufrufe von bp3flash.py an die im ZIP enthaltene
mmc-utils-Version (Commit c6692d5f9bf1ab6fbf5694a0b61477621cc4d016,
2026-09-10) an.

Wesentliche Punkte
------------------
- Nutzt subprocess.run() mit binären Ein-/Ausgabedaten und prüft den Exitcode.
- mmc-utils erhält beim write-block zuerst exakt 256 Datenbytes und danach
  den 32 Byte langen RPMB-Schlüssel über stdin.
- read-block gibt die 256 Nutzdatenbytes auf stdout aus und liest den Schlüssel
  über stdin.
- RPMB-Adressen sind Blockadressen: nach Block 2 folgt Block 3, nicht 258.
- Korrigiert Bytes/String-Auffüllung für Python 3.
- Prüft Rückgabelängen und RPMB-Metadaten, bevor Daten verwendet werden.
- Behält den bestehenden BP3-Partitions-/Wiederherstellungsablauf weitgehend bei.

Sicherheit / Grenzen
-------------------
- Nicht auf einer VU+ Duo 4K mit realem eMMC/RPMB getestet.
- Vor dem Einsatz auf dem Receiver sichern und zunächst nur auf einem
  entbehrlichen Testgerät bzw. nach Möglichkeit mit ungefährlichen
  Leseoperationen prüfen.
- RPMB-Schlüsselregistrierung ist eine einmalige, irreversible Operation.
  Sie darf nicht zu Testzwecken ausgeführt werden.
- Das Skript behält die ursprüngliche Logik bei, die bei fehlender Magic Number
  eine Schlüsselregistrierung und anschließend einen RPMB-Backupversuch
  auslösen kann. Vor dem produktiven Einsatz sollte dieser Ablauf auf der Box
  bewusst geprüft werden.
- Die ursprüngliche Partitionssuche und Shell-Aufrufe wurden nicht vollständig
  neu entworfen; Fokus ist die mmc-utils-RPMB-Schnittstelle.
