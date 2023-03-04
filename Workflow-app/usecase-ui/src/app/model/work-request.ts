export class WorkRequest {

    constructor(
        public id:number,
        public referenceNumber:string,
        public customerName:string,
        public workType:string,
        public status:string,
        public documents: any,
        public dateCreated: Date,
        public lastUpdated: Date,
        public actionComment:string = '',
        public sourceId: number
    ){

    }
}
