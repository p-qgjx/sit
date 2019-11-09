let address = 'http://xingyikeji.natapp1.cc/'
function postPromise(params, path) {
    return new Promise((resolve, reject) => {
       $.ajax({
        type: "POST",
        url: address + path,
        dataType:'JSON',
        data: params,
        success: res => {
          resolve(res)
        },
        fail: rej => {
          reject(rej)
        }
      });
    })
};

function jsonPromise(params, path) {
    return new Promise((resolve, reject) => {
       $.ajax({
        type: "POST",
        url: address + path,
        data: params,
        dataType:'JSON',
        contentType: 'application/json',
        success: res => {
          resolve(res)
        },
        fail: rej => {
          reject(rej)
        }
      });
    })
};
function getPromise(params, path) {
    return new Promise((resolve, reject) => {
       $.ajax({
        type: "GET",
        url: address + path,
        data: params,
        dataType:'JSON',
        success: res => {
          resolve(res)
        },
        fail: rej => {
          reject(rej)
        }
      });
    })
};



function upFile(params, path) {
  return new Promise((resolve, reject) => {
    $.ajax({
      url: address + path,
      type: "POST",
      data: params,
      cache: false,
      contentType: false,
      processData: false,
      success: res => {
        resolve(res)
      },
      fail: rej => {
        reject(rej)
      }
    });
  })
};

function postAjax(interface, datas,url,then) {
  let isTrue = false;
  $.ajax({
      type: "POST",
      dataType:'JSON',
      url: address + interface,
      data: datas,
      async:false, 
      error: function(error) {
          console.log(error);
      },
      success: function(res) {
        if( res.flag == 1) { 
          then.$message({
            message: '您没有权限',
            type: 'warning'
          }); 
          
        }else {
          if(url != '') {
            window.location.href = url;
          }else {
            isTrue = true
          }
        }
       
      }
  });
  return isTrue 
}


Storage.get = function(name) {
  let info = localStorage.getItem(name);
  return JSON.parse(info).userid
}