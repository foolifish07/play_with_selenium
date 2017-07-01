
# Launch server

## launch a chrome standalone server
```bash
$ ./launch_chrome_standalone.sh
```
remote url should be: `http://127.0.0.1:4444/wd/hub`
VNC port at 5900, password: `secret`

## launch a selenium grid 
```bash
$ ./up_grid.sh
```
remote url should be: `http://127.0.0.1:4444/wd/hub`

VNC port at 5901, 5902, password: `secret`

visit website and check it:
http://localhost:4444/grid/console