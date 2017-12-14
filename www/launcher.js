cordova.define("com.launcher.launcher.launcher", function(require, exports, module) {
/*global cordova, module*/

module.exports = {
    launch: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Launcher", "launch", name);
    }
};

});
