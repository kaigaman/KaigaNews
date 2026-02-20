export interface RadioStation {
  id: number;
  name: string;
  frequency: string;
  streamUrl: string;
  logo: string;
  color: string;
}

export const RADIO_STATIONS: RadioStation[] = [
  {
    id: 1,
    name: 'Radio Simba',
    frequency: '98.4 FM',
    streamUrl: 'http://radiosimba.com:8000/live',
    logo: 'https://radiosimba.com/wp-content/uploads/2021/06/logo-1.png',
    color: '#e94560',
  },
  {
    id: 2,
    name: 'Capital FM',
    frequency: '91.3 FM',
    streamUrl: 'http://stream.ved Rwanda:8000/capital',
    logo: 'https://capitalfm.co.ug/wp-content/uploads/2020/01/capital-fm-uganda-logo.png',
    color: '#0f3460',
  },
  {
    id: 3,
    name: 'NBS Radio',
    frequency: '92.2 FM',
    streamUrl: 'https://stream.nbsmedia.co.ug/nbsradio',
    logo: 'https://nbsmedia.co.ug/wp-content/uploads/2021/06/nbs-logo.png',
    color: '#16c79a',
  },
  {
    id: 4,
    name: 'KFM',
    frequency: '103.8 FM',
    streamUrl: 'https://kfmstream.siminetworks.com/kfm',
    logo: 'https://kfm.co.ug/wp-content/uploads/2020/03/kfm-logo.png',
    color: '#11999e',
  },
  {
    id: 5,
    name: 'Beat FM',
    frequency: '88.6 FM',
    streamUrl: 'https://stream.beat.co.ug/beat',
    logo: 'https://beat.co.ug/wp-content/uploads/2021/02/beat-fm-uganda.png',
    color: '#f64c72',
  },
  {
    id: 6,
    name: 'Radio One',
    frequency: '90.0 FM',
    streamUrl: 'https://radioonefm.siminetworks.com/radioone',
    logo: 'https://radioonefm.co.ug/wp-content/uploads/2020/01/radio-one-logo.png',
    color: '#fcbad3',
  },
  {
    id: 7,
    name: 'CBS Radio',
    frequency: '89.2 FM',
    streamUrl: 'https://stream.cbsmedia.co.ug/cbs',
    logo: 'https://cbsmedia.co.ug/wp-content/uploads/2021/01/cbs-logo.png',
    color: '#00b8a9',
  },
  {
    id: 8,
    name: 'Sanyuka FM',
    frequency: '93.3 FM',
    streamUrl: 'https://sanyukafm.siminetworks.com/sanyuka',
    logo: 'https://sanyukafm.co.ug/wp-content/uploads/2020/03/sanyuka-logo.png',
    color: '#845ec2',
  },
];
