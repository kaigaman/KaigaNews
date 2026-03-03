package com.kaiganews.app.data.local

import com.kaiganews.app.domain.model.RadioStation

object RadioStationsData {
    
    // Using verified working stream URLs
    // BBC World Service is known to work reliably
    
    val ugandanRadioStations = listOf(
        RadioStation(
            id = "bbc_world_service",
            name = "BBC World Service",
            streamUrl = "https://stream.live.vc.bbcmedia.co.uk/bbc_world_service",
            logoUrl = null,
            description = "BBC World Service English - News"
        ),
        RadioStation(
            id = "bbc_news",
            name = "BBC News",
            streamUrl = "https://stream.live.vc.bbcmedia.co.uk/bbc_news",
            logoUrl = null,
            description = "BBC News 24/7"
        ),
        RadioStation(
            id = "bbc_radio1",
            name = "BBC Radio 1",
            streamUrl = "https://stream.live.vc.bbcmedia.co.uk/bbc_radio_one",
            logoUrl = null,
            description = "BBC Radio 1 - Pop Music"
        ),
        RadioStation(
            id = "bbc_radio2",
            name = "BBC Radio 2",
            streamUrl = "https://stream.live.vc.bbcmedia.co.uk/bbc_radio_two",
            logoUrl = null,
            description = "BBC Radio 2 - Adult Contemporary"
        ),
        RadioStation(
            id = "bbc_radio4",
            name = "BBC Radio 4",
            streamUrl = "https://stream.live.vc.bbcmedia.co.uk/bbc_radio_fourfm",
            logoUrl = null,
            description = "BBC Radio 4 - Talk & News"
        ),
        RadioStation(
            id = "bbc_radio5live",
            name = "BBC Radio 5 Live",
            streamUrl = "https://stream.live.vc.bbcmedia.co.uk/bbc_radio_five_live",
            logoUrl = null,
            description = "BBC Radio 5 Live - Sports"
        ),
        RadioStation(
            id = "bbc_afrique",
            name = "BBC Afrique",
            streamUrl = "https://stream.live.vc.bbcmedia.co.uk/bbc_afrique",
            logoUrl = null,
            description = "BBC French for Africa"
        ),
        RadioStation(
            id = "bbc_swahili",
            name = "BBC Swahili",
            streamUrl = "https://stream.live.vc.bbcmedia.co.uk/bbc_swahili",
            logoUrl = null,
            description = "BBC Swahili Service"
        ),
        RadioStation(
            id = "voa_africa",
            name = "VOA Africa",
            streamUrl = "https://voaafrique.leanstream.co/voaafriqueFM.mp3",
            logoUrl = null,
            description = "Voice of America Africa"
        ),
        RadioStation(
            id = "rfi_monde",
            name = "RFI Monde",
            streamUrl = "https://stream.rfi.fr/rfi/monde/mid/1012/stream.mp3",
            logoUrl = null,
            description = "Radio France Internationale"
        ),
        RadioStation(
            id = " DW",
            name = "DW Africa",
            streamUrl = "https://dw-afr-01.sslstream.dw.com/dwafr_128",
            logoUrl = null,
            description = "Deutsche Welle Africa"
        ),
        RadioStation(
            id = "radio_france",
            name = "Radio France",
            streamUrl = "https://icecast.radiofrance.fr/franceinter-midfi.mp3",
            logoUrl = null,
            description = "Radio France Inter"
        ),
        RadioStation(
            id = "classic_fm",
            name = "Classic FM",
            streamUrl = "https://media-ice.musicradio.com/ClassicFMMP3",
            logoUrl = null,
            description = "Classical Music"
        ),
        RadioStation(
            id = "jazz_fm",
            name = "Jazz FM",
            streamUrl = "https://edge-bauermz-03-gos2.sharp-stream.com/jazzfm.mp3",
            logoUrl = null,
            description = "Jazz Music"
        ),
        RadioStation(
            id = "capital_fm_uk",
            name = "Capital FM UK",
            streamUrl = "https://media-ice.musicradio.com/CapitalMP3",
            logoUrl = null,
            description = "Capital FM UK"
        ),
        RadioStation(
            id = "smooth_radio",
            name = "Smooth Radio",
            streamUrl = "https://media-ice.musicradio.com/SmoothMP3",
            logoUrl = null,
            description = "Smooth Radio UK"
        ),
        RadioStation(
            id = "heart_radio",
            name = "Heart Radio",
            streamUrl = "https://media-ice.musicradio.com/HeartMP3London",
            logoUrl = null,
            description = "Heart Radio UK"
        ),
        RadioStation(
            id = "absolute_radio",
            name = "Absolute Radio",
            streamUrl = "https://ais.absoluteradio.co.uk/absolute.mp3",
            logoUrl = null,
            description = "Absolute Radio UK"
        ),
        RadioStation(
            id = "kiss_fm",
            name = "Kiss FM",
            streamUrl = "https://media-ice.musicradio.com/KissFMMP3",
            logoUrl = null,
            description = "Kiss FM UK"
        ),
        RadioStation(
            id = "talk_sport",
            name = "TalkSPORT",
            streamUrl = "https://talksport.streamguys1.com/talksport",
            logoUrl = null,
            description = "TalkSPORT - Sports Radio"
        )
    )
}
