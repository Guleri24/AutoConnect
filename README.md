# AutoConnect

AutoConnect is WIP program to automatically connect Cisco AnyConnect VPN using WinAuth Authentication Code in Windows10


https://github.com/Guleri24/AutoConnect/assets/43719098/a4f539c6-6a9c-4e5e-b250-d9ed4ed15ebc

Ahem, cough cough... Oops! I accidentally recorded the background music too. Consider it a bonus track and enjoy the unexpected musical accompaniment to your day!

## Requirements

* Java 20
* Maven 3.9.3 (Optional)
* WinAppDriver https://github.com/microsoft/WinAppDriver

## Running from Source

1. Clone the repo

   git clone https://github.com/Guleri24/AutoConnect.git

2. Open `AnyConnect` folder

   `cd AnyConnect`

3. Install project dependencies

   `mvn install`

4. Compile

   `mvn compile`

5. Run

   `mvn test`

If you don't want to change the current Maven version using you can use Maven wrapper included.
Replace `mvn <command>` with `.\mvnw.cmd <command>`

