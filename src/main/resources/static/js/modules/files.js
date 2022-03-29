const $files = {
    filesMap: new Map(),
    init: function () {
        var _this = this;

        $(".file-input").on("change", function() {
            $.each(this.files, function (idx, file) {
                _this.addFile(file);
            });
        })
    },
    removeFile: function (file) {
        const filename = $(file).siblings('span').text();
        this.filesMap.delete(filename);
        $(file).closest('.file_wraper').remove();
    },
    addFile: function (file) {
        let filename = file.name;

        if (this.filesMap[filename]) return;

        this.filesMap.set(filename, file);
        const template = `
                    <div class="file_wraper">
                        <button type="button" onclick="$files.removeFile(this)">제거</button>
                        <span>${filename}</span>
                    </div>`;

        $(".filenames").append(template);
    },
    getFiles: function () {
        return Array.from(this.filesMap.values());
    },
}

export default $files;
