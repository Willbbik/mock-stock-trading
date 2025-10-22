import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    vus: 1000, // 동시에 1000명
    duration: '10m', // 10분 동안
};

export default function () {
    http.get('http://host.docker.internal:8080/api/coin/test');
    sleep(1);
}