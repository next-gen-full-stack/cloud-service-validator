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
      }
    ]
  }
];