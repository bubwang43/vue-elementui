'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  // API_URL: '"http://localhost:8088"'
  API_URL: '"http://192.168.28.172:8080/elementui-api"'
})
