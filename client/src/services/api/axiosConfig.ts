import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080/api/v1';

axios.interceptors.request.use((config) => {
  config.headers['Access-Control-Allow-Origin'] = '*';
  config.headers['Access-Control-Allow-Methods'] = 'GET, POST, PUT, DELETE';
  config.headers['Access-Control-Allow-Headers'] = 'Origin, Content-Type, Accept, Authorization';
  return config;
});

export default axios;
