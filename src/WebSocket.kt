package com.autumnsun

object  WebSocket {
    const val loginRequest = "{\"is_request\":true,\"id\":8,\"params\"" +
            ":[{\"username\":\"demo\",\"password\":\"123456\"}],\"method\":\"Authenticate\"}"
    const val homeRequest = "\"{\\\"is_request\\\":true,\\\"id\\\":5,\\\"params\\\":[{}],\\\"method\\\":\\\"GetControlList\\\"}"
    const val lightRequest = "{\\\"is_request\\\":true,\\\"id\\\":84,\\\"params\\\":[{\\\"id\\\":\\\"a2830d60-ddff-4dad-8f3d-dfca0ded2462\\\",\\\"value\\\":1}\\r\\n],\" +\n" +
            "\"\\\"method\\\":\\\"UpdateControlValue\\\"}"
    const val loginResponse = "{\"id\":792,\"params\":" +
            "[\"demo\"],\"method\":\"OnAuthenticated\",\"error\":null,\"is_request\":true}"
    const val homeResponse = "{\"id\":5,\"params\":[{\"data\":[{\"id\":" +
            "\"0516e98c-012d-4487-9007-9cb4cebb0de5\",\"name\"" + ":" +
            "\"Panel\r\nKombi\",\"type_id\":\"7\",\"bridge_device_id\":\"b01073b2-f142-4a79-907e-a90f0b061290\",\"current\r\n_value\":0,\"slot\":0,\"is_active\":true,\"temperature_settings\":" +
            "{\"has_heating\":true,\"has_cooling\":true,\"bridge_device_id\":\"b01073b2-f142-4a79-907e-a90f0b061290\",\"virtual_control_id\":\"\",\"inp\r\nut_id\":\"2e7069aa-283b-4fbc-9ff5-b61985ec70f8\",\"is_mode_heating\":true,\"whole\":24,\"fraction\r\n\":0},\"area_id\":\"14a46e23-ca1a-4d4a-9b26-9c548a5605e8\",\"parameters\":{\"default_value\":0,\"\r\noutput_number\":0,\"should_output_reverse\":false,\"should_remember_last_value\":false}},{\"id\":\r\n\"a2830d60-ddff-4dad-8f3d-dfca0ded2462\",\"name\":\"Panel\r\nLamba\",\"type_id\":\"1\",\"bridge_device_id\":\"b01073b2-f142-4a79-907e-a90f0b061290\",\"current\r\n_value\":1,\"slot\":1,\"is_active\":true,\"area_id\":\"14a46e23-ca1a-4d4a-9b26-9c548a5605e8\",\"par\r\nameters\":{\"default_value\":0,\"end_time\":\"\",\"is_notification\":false,\"output_number\":99,\"should_\r\noutput_reverse\":false,\"should_remember_last_value\":true,\"start_time\":\"\"}}]}],\"method\":\"GetC\r\nontrolList\"," +
            "\"error\":null,\"is_request\":false}"
    const val lightResponse = "\"{\\\"id\\\":852,\\\"params\\\":[{\\\"entity\\\":\" +\n" +
            "\"{\\\"id\\\":\\\"a2830d60-ddff-4dad-8f3d-dfca0ded2462\\\",\" +\n" +
            "\"\\\"name\\\":\\\"Panel\\r\\nLamba\\\",\\\"type_id\\\":\\\"1\\\",\" +\n" +
            "\"\\\"bridge_device_id\\\":\\\"b01073b2-f142-4a79-907e-a90f0b061290\\\",\" +\n" +
            "\"\\\"current\\r\\n_value\\\":1,\\\"slot\\\":1,\\\"is_active\\\":true,\" +\n" +
            "\"\\\"area_id\\\":\\\"14a46e23-ca1a-4d4a-9b26-9c548a5605e8\\\",\" +\n" +
            "\"\\\"par\\r\\nameters\\\":{\\\"default_value\\\":0,\\\"end_time\\\":\" +\n" +
            " \"\\\"\\\",\\\"is_notification\\\":false,\\\"output_number\\\":99,\" +\n" +
            "\"\\\"should_\\r\\noutput_reverse\\\":false,\\\"should_remember_last_value\\\":true,\" +\n" +
            "\"\\\"start_time\\\":\\\"\\\"}},\\\"type\\\":\\\"control\\\"}],\\\"\\r\\nmethod\\\":\" +\n" +
            "\"\\\"OnEntityUpdated\\\",\" + \"\\\"error\\\":null,\\\"is_request\\\":true}\""
}