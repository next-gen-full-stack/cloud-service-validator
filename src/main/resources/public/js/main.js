(function (list) {
  'use strict'

  $('[data-toggle="offcanvas"]').on('click', function () {
    $('.offcanvas-collapse').toggleClass('open')
  });

  var jContainer = $('.ui-cloud-providers');
  var jRequests = {};
  for(var i in list) {

    var cloud = list[i];
    var cloudId = 'cloud-'+cloud.id;
    var jCloudContainer = $('<div class="my-3 p-3 bg-white rounded shadow-sm"></div>');
    jCloudContainer.attr('id', cloudId);
    var jSubTitle = $('<h6 class="border-bottom border-gray pb-2 mb-0"></h6>');
    jSubTitle.text(cloud.group);
    jCloudContainer.append(jSubTitle);

    var jStatusBody = $('<div class="table-responsive-md"><table class="table"><thead><tr><th scope="col"></th><th scope="col">Service</th><th scope="col">Accessibility</th><th scope="col">Scalability</th><th scope="col">Performance</th><th scope="col"></th></tr></thead><tbody class="ui-table-body"></tbody></table></div>');

    jCloudContainer.append(jStatusBody);

    for(var j in cloud.endpoints) {

      var service = cloud.endpoints[j];
      var serviceId = 'svc-'+cloud.id+'-'+service.name.toLowerCase();

      jRequests[serviceId] = { url: service.path, name: service.name, cloud: cloudId };
    }

    jContainer.append(jCloudContainer);
  }

  var sendRequest = function() {

    var listOfKeys = Object.keys(jRequests);
    if(listOfKeys.length > 0) {
      var serviceId = listOfKeys[0];      
      var url = jRequests[serviceId].url;
      var name = jRequests[serviceId].name;
      var cloud = jRequests[serviceId].cloud;
      delete jRequests[serviceId];

      var cContainer = $('#' + cloud);
      var rRowContainer = cContainer.find('.ui-table-body');

      $.ajax({
        url: url, 
        success: function(data) {
          var colorCode = 'cc0000';
          if(data.accessibility) {
            colorCode = '28a745';
          }
          var row = '<tr><td><img alt="" class="mr-2 rounded" data-src="holder.js/16x16?theme=thumb&bg='+colorCode+'&fg='+colorCode+'&size=1" /></td><td>'+(data.service ? data.service : name)+'</td><td>'+(data.accessibility ? 'Yes' : 'No')+'</td><td>'+(data.scalability ? 'Yes' : 'No')+'</td><td>'+(data.responseTime ? (data.responseTime +'ms') : 'N/A')+'</td></tr>';
          rRowContainer.append(row);
          Holder.run({ });
          sendRequest();
        }, 
        error: function(err) {

          var row = '<tr><td><img alt="" class="mr-2 rounded" data-src="holder.js/16x16?theme=thumb&bg=cc0000&fg=cc0000&size=1" /></td><td>'+name+'</td><td>No</td><td>N/A</td><td>N/A</td></tr>';
          rRowContainer.append(row);
          Holder.run({ });
          sendRequest();
        }
      });
    }
  };

  sendRequest();

})(ENDPOINTS);