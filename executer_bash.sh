#!/bin/bash

#sends alert message to email
echo "Sensor tripped!" | mail -s "Test Subject" maserati.sec@gmail.com

#command to produce sound 
printf '\a'