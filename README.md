# Java - OOP

### Data
All the data in this project is from an app called 'WiggleWifi'.  
This app is an android app that gives us a lot of data on the device, like MAC address, SSID, lat, lon, alt, etc.  
All the data is stored on '.csv' files.

### Wifi Scan
Wifi scan is defiend as one record from the app.

### Wifi Scans 
Wifi scans is defiend as the top ten most strongest 'Wifi scan'-s by signal.

### Writing Package
In the Writing Package I used openCSV to simply write the data to '.csv' file and JAK to write it to '.kml' file so we can see our data on Google Earth.

### Algorithems
Algorithems that used to find the **estimated location** of a scan using the previous data we have.
In this package I used 2 algorithems:
  1. The first naive algorithem is to find the strongest scan by MAC and return its location.
  2. The second and the smarter algorithem, takes all the scans with the correct MAC address, and calculate it with the signal so we can have weighted center point that provide us more accuracy.







