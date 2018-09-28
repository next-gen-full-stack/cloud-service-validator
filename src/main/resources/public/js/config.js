var ENDPOINTS = [
  {
    id: 'azure',
    group: 'Microsoft Azure',
    endpoints: [
      {
        name: 'Kubernetes',
        path: '/api/v1/ping/azure/k8s'
      },
      {
        name: 'Redis',
        path: '/api/v1/ping/redis'
      },
      {
        name: 'Mongo',
        path: '/api/v1/ping/mgdb'
      },
      {
          name: 'mysql',
          path: '/api/v1/ping/azure/mysql'
      },
      {
          name: 'postgresql',
          path: '/api/v1/ping/azure/postgresql'
      }
    ]
  },
  {
    id: 'alicloud',
    group: 'Ali Cloud',
    endpoints: [
      {
        name: 'Kubernetes',
        path: '/api/v1/ping/k8s'
      },
      {
          name: 'mysql',
          path: '/api/v1/ping/ali/mysql'
      },
      {
          name: 'postgresql',
          path: '/api/v1/ping/ali/postgresql'
      }
    ]
  }
];