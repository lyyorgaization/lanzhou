const webpack = require('webpack');
module.exports = {
    publicPath: '/admin/',
    productionSourceMap: false,
    devServer: {
        proxy: {
            '/omp': {
                target: 'http://119.3.5.25/',
                changeOrigin: true
            },
            '/data': {
                target: 'http://119.3.5.25/',
                changeOrigin: true
            }
        }
    },
    configureWebpack: {
        plugins: [
            new webpack.ProvidePlugin({
                'window.Quill': 'quill/dist/quill.js',
                'Quill': 'quill/dist/quill.js'
            })
        ]
    },
};