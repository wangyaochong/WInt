var  proxy = require('proxy-middleware');
var proxyRoutes = [
    '/api',
    '/management',
    '/swagger-resources',
    '/v2/api-docs',
    '/h2-console'
];
function endsWith(str, suffix) {
    return str.indexOf('/', str.length - suffix.length) !== -1;
}
var requireTrailingSlash = proxyRoutes.filter(function (r) {
    return endsWith(r, '/');
}).map(function (r) {
    // Strip trailing slash so we can use the route to match requests
    // with non trailing slash
    return r.substr(0, r.length - 1);
});
var proxies = [
    // Ensure trailing slash in routes that require it
    function (req, res, next) {
        requireTrailingSlash.forEach(function (route){
            if (url.parse(req.url).path === route) {
                res.statusCode = 301;
                res.setHeader('Location', route + '/');
                res.end();
            }
        });

        next();
    }
]
var historyApiFallback = require('connect-history-api-fallback');
var gulpModules = {
    gulp: require('gulp'),
    gulpUtil: require('gulp-util'),
    gulpPug: require('gulp-pug'),
    gulpConcat: require('gulp-concat'),
    gulpJshint: require('gulp-jshint'),
    gulpReplace: require('gulp-replace'),
    gulpPlumber: require('gulp-plumber'),
    gulpNgAnnotate:require('gulp-ng-annotate'),
    gulpUglify:require('gulp-uglify'),
    gulpSass:require('gulp-sass'),
    browserSync:require("browser-sync").create()
}
var processPath = {
    js2oneSrcPath: ['../beforeDist/srcJsAB/begin.js', '../beforeDist/srcJsAB/state.js', '../beforeDist/srcJsAB/stateTest.js', '../beforeDist/srcJsAB/end.js', '../beforeDist/srcJsAndPug/**/*.js','../beforeDist/srcJsAndPug/*.js','../beforeDist/srcJsAndPug/**/**/*.js'],
    js2onePath: '../dist/app/',
    js2oneLibPath: [
        //jquery
        '../beforeDist/libJs/jquery.js',
        //bootstrap
        '../beforeDist/libJs/bootstrap.js',
        //angularJS
        '../beforeDist/libJs/angular.js',
        //ngtable
        '../beforeDist/libJs/ng-table.js',
        //angular-ui-router
        '../beforeDist/libJs/angular-ui-router.js',
        //angular动画插件
        '../beforeDist/libJs/angular-animate.js',
        //angular手机触屏插件
        '../beforeDist/libJs/angular-touch.js',
        //ui-bootstrap，angular bootstrap插件
        '../beforeDist/libJs/ui-bootstrap.js',
        '../beforeDist/libJs/ui-bootstrap-tpls.js',

        //datepicker#日期选择插件
        '../beforeDist/libJs/bootstrap-datepicker.js',
        '../beforeDist/libJs/bootstrap-datepicker.zh-CN.min.js',
        //autosize#textarea自动变化尺寸插件
        // '../beforeDist/libJs/autosize.js',
        // slick#滑动图片插件
        // '../beforeDist/libJs/slick.js',
        //bootstrap-fileinput#文件上传插件
        '../beforeDist/libJs/canvas-to-blob.js',
        '../beforeDist/libJs/sortable.js',
        '../beforeDist/libJs/fileinput.js',
        '../beforeDist/libJs/purify.js',
        '../beforeDist/libJs/theme.js',
        '../beforeDist/libJs/zh.js',


        //bootstrap-select插件
        // '../beforeDist/libJs/bootstrap-select.js',
        // '../beforeDist/libJs/defaults-zh_CN.js',
        //angular-ui-select
        '../beforeDist/libJs/select.js',
        //angular-ui-select依赖sanitize.js
        '../beforeDist/libJs/angular-sanitize.js',
        //视频播放插件
        // '../beforeDist/libJs/video.js',
        // '../beforeDist/libJs/videojs-ie8.js',


        '../beforeDist/libJs/lodash.js',
        // '../beforeDist/libJs/xeditable.js',
        //侧边菜单插件
        // '../beforeDist/libJs/slideout.js',
        //一个效果插件
        // '../beforeDist/libJs/easing.js',
    ],
    css2oneLibPath: [
        // '../beforeDist/libCss/slick.css',
        // '../beforeDist/libCss/slick-theme.css',
        // '../beforeDist/libCss/AdminLTE.css',
        // '../beforeDist/libCss/bootstrap-select.css',

        '../beforeDist/libCss/bootstrap.css',
        '../beforeDist/libCss/ng-table.css',
        '../beforeDist/libCss/fileinput.css',
        '../beforeDist/libCss/bootstrap-datepicker3.css',
        //ui.bootstrap.css
        '../beforeDist/libCss/ui-bootstrap-csp.css',
        // angular-ui-select
        '../beforeDist/libCss/select.css',
        //video.js
        // '../beforeDist/libCss/video-js.css',
        // '../beforeDist/libCss/xeditable.css',
    ],
    css2oneSrcPath: ['../dist/srcCss/*.css'],
    css2onePath: '../dist/app/',
    sass2cssSrcPath:['../beforeDist/srcScss/*.scss'],
    sass2cssDestPath:'../dist/srcCss/',
    pugPath:['../beforeDist/srcJsAndPug/**/*.pug','../beforeDist/srcJsAndPug/*.pug','../beforeDist/srcJsAndPug/**/**/*.pug'],
    htmlPath:'../dist/html/'
}
var taskNames = {
    watch: 'watch',
    libMany2one: 'libMany2one',
    cssManyToOne:'cssManyToOne',
    jsManyToOne:'jsManyToOne',
    sass2css:'sass2css',
    pugToHtml:'pugToHtml',
}

var optionsForReplace = {
    beginToReplace: '{"##begin##"})',
    beginAfterReplace: '{',
    endToReplace: '{"##end##"}',
    endAfterReplace: '})'
}
var optionsForPlumber = {
    errorHandler: true
}
var needCompress=false;
function sass2css(sassPath,cssPath) {
    gulpModules.gulp.src(sassPath)
        .pipe(gulpModules.gulpSass().on('error',gulpModules.gulpSass.logError))
        .pipe(gulpModules.gulp.dest(cssPath))
        // .pipe(gulpModules.browserSync.reload({stream:true}))
}
function pugToHtml(pugPath, htmlPath) {
    gulpModules.gulp.src(pugPath)
        .pipe(gulpModules.gulpPlumber(optionsForPlumber))
        .pipe(gulpModules.gulpPug({ pretty: true }))
        .pipe(gulpModules.gulp.dest(htmlPath))
        // .pipe(gulpModules.browserSync.reload({stream:true}))
}
function many2oneCSS(jsPath, onePath, fileName) {
    gulpModules.gulp.src(jsPath)
        .pipe(gulpModules.gulpPlumber(optionsForPlumber))
        .pipe(gulpModules.gulpConcat(fileName))
        .pipe(gulpModules.gulpReplace(optionsForReplace.beginToReplace, optionsForReplace.beginAfterReplace))
        .pipe(gulpModules.gulpReplace(optionsForReplace.endToReplace, optionsForReplace.endAfterReplace))
        .pipe(gulpModules.gulp.dest(onePath))
        // .pipe(gulpModules.browserSync.reload({stream:true}))

}
function many2oneJS(jsPath, onePath, fileName) {
    gulpModules.gulp.src(jsPath)
        .pipe(gulpModules.gulpPlumber(optionsForPlumber))
        .pipe(gulpModules.gulpConcat(fileName))
        .pipe(gulpModules.gulpReplace(optionsForReplace.beginToReplace, optionsForReplace.beginAfterReplace))
        .pipe(gulpModules.gulpReplace(optionsForReplace.endToReplace, optionsForReplace.endAfterReplace))
        .pipe(gulpModules.gulpNgAnnotate())
        .pipe(needCompress==true?
            gulpModules.gulpUglify({mangle: {except: ['require' ,'exports' ,'module' ,'$']}}):gulpModules.gulpUtil.noop() )
        .pipe(gulpModules.gulp.dest(onePath))
        // .pipe(gulpModules.browserSync.reload({stream:true}))
}
gulpModules.gulp.task(taskNames.sass2css,function () {
    sass2css(processPath.sass2cssSrcPath,processPath.sass2cssDestPath);
})
gulpModules.gulp.task(taskNames.pugToHtml,function () {
    pugToHtml(processPath.pugPath,processPath.htmlPath);
})
gulpModules.gulp.task(taskNames.libMany2one, function () {
    many2oneJS(processPath.js2oneLibPath, processPath.js2onePath, 'lib.js')//把多个js库合成一个lib.js
    many2oneCSS(processPath.css2oneLibPath, processPath.css2onePath, 'lib.css')//把多个css库合成一个
})
gulpModules.gulp.task(taskNames.cssManyToOne, function () {
    many2oneCSS(processPath.css2oneSrcPath, processPath.css2onePath, 'app.css')//把多个css源码合成一个
})
gulpModules.gulp.task(taskNames.jsManyToOne, function () {
    many2oneJS(processPath.js2oneSrcPath, processPath.js2onePath, 'app.js')//把多个js源码合成一个app.js
})
function browserSync() {
    gulpModules.gulp.watch(processPath.htmlPath+"/**/*.html").on('change',  reload);
    gulpModules.gulp.watch(processPath.css2onePath+"/**/*.css").on('change', reload);
    gulpModules.gulp.watch(processPath.js2onePath+"/**/*.js").on('change', reload);
    gulpModules.browserSync.init({
        open: true,
        port: 3000,
        proxy:"127.0.0.1:8080/WInt",
        files:"**",
        // files:"*.css,*.html,*.js",
        // server: {
        //     baseDir: "./",
        //     middleware: [historyApiFallback()]
        // }
    });
}
function reload() {
    setTimeout(function () {
        gulpModules.browserSync.reload();
    },1000)
}
gulpModules.gulp.task(taskNames.watch, function () {
    //监视源码是否有修改，并执行相应的任务
    gulpModules.gulp.watch([processPath.sass2cssSrcPath],[taskNames.sass2css]);
    gulpModules.gulp.watch([processPath.js2oneLibPath],[taskNames.libMany2one]);
    gulpModules.gulp.watch([processPath.css2oneLibPath],[taskNames.libMany2one]);
    gulpModules.gulp.watch([processPath.js2oneSrcPath],[taskNames.jsManyToOne]);
    gulpModules.gulp.watch([processPath.css2oneSrcPath],[taskNames.cssManyToOne]);
    gulpModules.gulp.watch([processPath.pugPath],[taskNames.pugToHtml]);
    //如果是监听多个文件，使用一个数组就可以
    gulpModules.gulp.watch([processPath.js2oneSrcPath, processPath.js2oneLibPath, processPath.css2oneSrcPath, processPath.css2oneLibPath,processPath.sass2cssSrcPath], function (event) {
        //console.log('File ' + event.path + ' was ' + event.type + ', running tasks...');
        gulpModules.gulpUtil.log("File-->" + event.path);
        gulpModules.gulpUtil.log("Event:" + event.type);
    });
     // browserSync();
});

gulpModules.gulp.task('default', [taskNames.watch, taskNames.sass2css,taskNames.pugToHtml, taskNames.libMany2one,taskNames.jsManyToOne,taskNames.cssManyToOne]);
