@echo off
echo Compiling Library Management System...

REM Create bin directory if it doesn't exist
if not exist "bin" mkdir bin

REM Compile Java files (assuming MySQL connector is in lib folder)
javac -d bin -cp "lib/*" src/*.java

if %errorlevel% equ 0 (
    echo Compilation successful!
    echo Running Library Management System...
    java -cp "bin;lib/*" LibraryManagement
) else (
    echo Compilation failed!
    pause
)