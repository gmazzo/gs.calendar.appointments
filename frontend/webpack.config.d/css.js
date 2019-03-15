config.module.rules.push({
    test: /\.css$/,
    use: ['style-loader', 'css-loader']
});

config.module.rules.push({
    test: /\.less$/,
    use: ['style-loader', 'css-loader', 'less-loader']
});