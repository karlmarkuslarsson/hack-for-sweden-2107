# Backend hack-for-sweden-2107
You can access the server on `http://hack-for-sweden.filiplindqvist.com:3000/`

## Run locally
Install node 7.+ locally.
* `cd backend`
* `npm install`
* `npm install -g pm2`
* `pm2 start --watch app.js`

## Deploy to prod
Make sure you have vagrant installed.
* Create a `.env` file in the `backend/` folder.
* Add your Digital Ocean Token as a line like `DIGITAL_OCEAN_TOKEN=555....78c`.  
* `vagrant up`

App will now watch for file changes.
* To update files on server, run
`vagrant rsync`

# Frontend 
Android stuff...