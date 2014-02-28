var NODE_ENV = process.env.NODE_ENV || 'development';

module.exports = function (grunt) {
  grunt.initConfig({
    pkg: 'package.json',
    less: {
      development: {
        options: {sourceMap: true},
        files: {
          'WebContent/resource/style.css': 'WebContent/resource/style.less',
        }
      },
      production: {
        options: {compress: true},
        files: {
          'WebContent/resource/style.css': 'WebContent/resource/style.less',
        }
      }
    },
  });

  grunt.loadNpmTasks('grunt-contrib-less');

  grunt.registerTask('build', ['less:development']);
};
