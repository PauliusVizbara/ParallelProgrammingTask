# P170B328 Lygiagretusis programavimas. Individuali užduotis sesijos metu

## Duomenys

Duomenys gaunami iš [Twitch API](https://api.twitch.tv/helix/streams)

## Reikalavimai

Programos paleidimas vygdomas su [Maven](http://apache.mirror.vu.lt/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip)


## Paleidimas

Dependencies instaliavimas
```
mvn clean install
```

Paleidimas
```
mvn exec:java -P egzaminas
```


## Example's License

Copyright (c) 2015 Open Credo Ltd, Licensed under MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
