@echo off
set /p SRCURL="Enter source URL: "
set /p DESTURL="Enter dstination URL: "
curl %SRCURL% -o %DESTURL%
::curl -d @%SRCURL% -u sroot %DESTURL%
echo "File transfered successfully.." 