package com.minja.application;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.minja.interceptor.annotations.Scanner;

@Scanner("com.minja")
@ApplicationPath("/rest")
public class JaxRsApp extends Application {

}
