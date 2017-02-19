description "Locactitracker"
author "Xavier Butty"

start on runlevel [3]
stop on shutdown

expect fork

script
    cd /etc/locactitracker
    exec sudo java -jar target/locactitracker-1.0-SNAPSHOT.jar >/var/log/locactitrackersauceconnect.log 2>&1
    emit locactitracker_running
end script