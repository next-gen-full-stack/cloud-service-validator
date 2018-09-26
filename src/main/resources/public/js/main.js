$(function () {
  'use strict'

  $('[data-toggle="offcanvas"]').on('click', function () {
    $('.offcanvas-collapse').toggleClass('open')
  });

  var list = [
    {
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
      group: 'Ali Cloud', 
      endpoints: [
        {
          name: 'Kubernetes', 
          path: '/api/v1/ping/k8s'
        }
      ]
    }
  ];

  var jContainer = $('.ui-cloud-providers');
  for(var i in list) {

    var cloud = list[i];
    var jSubContainer = $('<div class="my-3 p-3 bg-white rounded shadow-sm"></div>');
    var jSubTitle = $('<h6 class="border-bottom border-gray pb-2 mb-0"></h6>');
    jSubTitle.text(cloud.group);
    jSubContainer.append(jSubTitle);

    for(var j in cloud.endpoints) {

      var service = cloud.endpoints[j];
      var serviceId = 'svc-'+service.name.toLowerCase();
      
      var jServiceContainer = $('<div class="media text-muted pt-3"></div>');
      jServiceContainer.attr('id', serviceId);

      // Image
      var jStatusImage = $('<img data-src="" alt="" class="mr-2 rounded" />');
      jServiceContainer.append(jStatusImage);
      jStatusImage.attr('data-src', 'holder.js/32x32?theme=thumb&bg=007bff&fg=007bff&size=1');
      // SubContainer of service
      var jStatusBody = $('<div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray"></div>');

      var jStatusTop = $('<div class="d-flex justify-content-between align-items-center w-100"></div>');
      var jStatusName = $('<strong class="text-gray-dark"></strong>');
      var jStatusButton = $('<a href="#" class="ui-refresh">Refresh</a>');
      

      var jStatusBottom = $('<span class="d-block"></span>');

      jStatusName.text(service.name);
      jStatusTop.append(jStatusName);
      jStatusTop.append(jStatusButton);
      jStatusBody.append(jStatusTop);
      jStatusBody.append(jStatusBottom);

      jStatusBottom.text('OK or Not OK');

      jServiceContainer.append(jStatusBody);
      jSubContainer.append(jServiceContainer);

      jContainer.append(jSubContainer);
    }
  }

});