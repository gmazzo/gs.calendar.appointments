config.module.rules.push(
    {
        test: /\.css$/,
        use: ['style-loader', 'css-loader']
    }, {
        test: /\.less$/,
        use: ['style-loader', 'css-loader', 'less-loader']
    }
);
