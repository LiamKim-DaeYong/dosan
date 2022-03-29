const $url = {
    getPath: function (extPath) {
        if(extPath && extPath[0] != '/') extPath = '/' + extPath;
        return location.pathname + (extPath? extPath : '');
    },
    getHost: function () {
        return location.hostname;
    },
    redirect: function (path) {
        location.href = path?path:this.getPath();
    },
}

export default $url;
