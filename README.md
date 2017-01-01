# Application

## Prerequisites

The `APP_CONFIG_PATH` environment variable must point to folder containing one or more `.properties` files

## Running

- build the image

`docker build . -t webapp`

- create a test .properties file

`echo "foo=bar" > test.properties`

- run the image

`docker run -v $(pwd):/data -e APP_CONFIG_PATH=/data -p 8082:8080 -it webapp`

## Usage

Open your web browser to

    `http://localhost:8080/app` to show the list of `.properties` files under the path specified in `APP_CONFIG_PATH`
     Clicking on a file will take you to a page which shows it's contents.

## Testing

`mvn clean test`




