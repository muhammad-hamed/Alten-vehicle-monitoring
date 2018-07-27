export class Page<T> {
    content: T[] ;
    pageable: any;
    totalElements: number;
    totalPages: number;
    last: boolean;
    size: number;
    number: number;
    numberOfElements: number;
    first: boolean;
    sort: any;

    constructor() {}
    setContent(content:T[]) {
      this.content = content;
    }
  }