module.exports = {
    publicPath: './',
    productionSourceMap: false,
    devServer: {
        proxy: {
            '/omp': {
                target: 'http://172.28.21.243',
                changeOrigin: true
            }
        }
    }
};