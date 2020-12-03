import {Injectable} from '@angular/core';
import {ApiService} from '../services/ApiService';
import {ConfigService} from '../services/ConfigService';

@Injectable()
export class FooService {

  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) {
  }

  getFoo() {
    return this.apiService.get(this.config.foo_url);
  }

}
